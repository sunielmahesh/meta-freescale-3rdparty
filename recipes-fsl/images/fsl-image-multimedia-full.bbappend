CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    libubootenv-bin \
    "
# Create boot_vfat.img partition file into deploy directory
create_boot_image() {
    cd ${DEPLOY_DIR_IMAGE}
    mkdir -p extlinux
    cp extlinux.conf extlinux/
    dd if=/dev/zero of=boot_vfat.img bs=1048576 count=64
    mkfs.vfat boot_vfat.img
    mcopy -i boot_vfat.img imx8mp-icore-mx8mp-ctouch2-of10.dtb ::imx8mp-icore-mx8mp-ctouch2-of10.dtb
    mcopy -i boot_vfat.img Image ::Image
    mcopy -i boot_vfat.img extlinux/ ::extlinux
}

python do_bundle_boot_image() {
    bb.build.exec_func('create_boot_image', d)
}

do_bundle_boot_image[depends] += "mtools-native:do_populate_sysroot "
do_bundle_boot_image[depends] += "dosfstools-native:do_populate_sysroot "
do_bundle_boot_image[depends] += "virtual/kernel:do_deploy virtual/bootloader:do_deploy"

addtask bundle_boot_image before do_image_wic

# images to build before building swupdate image
IMAGE_DEPENDS = "fsl-image-multimedia-full"

# images and files that will be included in the .swu image
SWUPDATE_IMAGES = "fsl-image-multimedia-full boot_vfat.img"

SWUPDATE_IMAGES_FSTYPES[fsl-image-multimedia-full] = ".ext4.gz"

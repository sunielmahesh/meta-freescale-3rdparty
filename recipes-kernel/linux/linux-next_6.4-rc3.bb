iSECTION = "kernel"
DESCRIPTION = "Linux next kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

DEPENDS += "rsync-native lzop-native bc-native"

SRCREV = "44c026a73be8038f03dbdeef028b642880cf1511"
SRCBRANCH = "master"
SRC_URI = " \
	git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git;branch=${SRCBRANCH};protocol=https \
	file://0001-arm64-dts-freescale-Add-Engicam-i.Core-MX8M-Plus-C.T.patch \
	file://0002-dt-bindings-arm-fsl-Add-Engicam-i.Core-MX8M-Plus-C.T.patch \
	file://0003-arm64-dts-imx8mp-icore-Enable-wdog1-node.patch \
	file://defconfig \
	"

LINUX_VERSION = "6.4-rc3"
PV = "${LINUX_VERSION}+git${SRCPV}"
S = "${WORKDIR}/git"

# Tell to kernel class that we would like to use our defconfig to configure the kernel.
# Otherwise, the --allnoconfig would be used per default which leads to mis-configured
# kernel.
#
# This behavior happens when a defconfig is provided, the kernel-yocto configuration
# uses the filename as a trigger to use a 'allnoconfig' baseline before merging
# the defconfig into the build.
#
# If the defconfig file was created with make_savedefconfig, not all options are
# specified, and should be restored with their defaults, not set to 'n'.
# To properly expand a defconfig like this, we need to specify: KCONFIG_MODE="--alldefconfig"
# in the kernel recipe include.
KCONFIG_MODE="--alldefconfig"

# We need to pass it as param since kernel might support more then one
# machine, with different entry points
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "(imx8mp-icore-ctouch2-of10)"

FILES_${KERNEL_PACKAGE_NAME}-base:append = " ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo"

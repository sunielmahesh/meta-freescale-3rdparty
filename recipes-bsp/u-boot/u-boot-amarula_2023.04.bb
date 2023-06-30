DESCRIPTION = "U-Boot v2023.04"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

require recipes-bsp/u-boot/u-boot.inc

PV = "v2023.04+git${SRCPV}"

DEPENDS += "bc-native dtc-native python3-setuptools-native flex-native bison-native"

SRCREV = "698c2bd364ce4122a0d0db82b5a8d842186b2fa4"
SRCBRANCH = "master"
SRC_URI = " \
	git://github.com/u-boot/u-boot.git;branch=${SRCBRANCH};protocol=https \
	file://0001-i.Core-MX8M-Plus-is-an-SODIMM-SoM-based-on-NXP-i.MX8.patch \
	file://0002-arm64-dts-Enable-wdog1.patch \
	file://0003-configs-icoremx8mp-Enable-bootcount.patch \
	"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy ${@oe.utils.ifelse(d.getVar('UBOOT_PROVIDES_BOOT_CONTAINER') == '1', 'imx-boot-container', '')}
PROVIDES += "u-boot"

COMPATIBLE_MACHINE = "(imx8mp-icore-ctouch2-of10)"

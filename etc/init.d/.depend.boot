TARGETS = console-setup resolvconf alsa-utils mountkernfs.sh ebtables ufw plymouth-log hostname.sh apparmor pppd-dns x11-common udev keyboard-setup mountdevsubfs.sh qemu-kvm brltty procps hwclock.sh checkroot.sh cryptdisks networking checkfs.sh urandom bootmisc.sh kmod mountnfs.sh mountall.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh cryptdisks checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
qemu-kvm: mountkernfs.sh udev
brltty: mountkernfs.sh udev
procps: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
cryptdisks: checkroot.sh udev
networking: resolvconf mountkernfs.sh urandom procps
checkfs.sh: cryptdisks checkroot.sh
urandom: hwclock.sh
bootmisc.sh: udev mountnfs-bootclean.sh checkroot-bootclean.sh mountall-bootclean.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh

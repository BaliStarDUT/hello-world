#!/bin/bash
PLATFORM=`uname -s`
FREEBSD=
SUNOS=
MAC=
AIX=
MINIX=
LINUX=
echo 
echo "Identifying the platform on which this installer is running on.."
echo "$PLATFORM"
if [ "$PLATFORM"="FreeBSD" ]
then 
	echo "This is FreeBSD system."
	FREEBSD=1
elif [ "$PLATFORM"="SunOs" ]
then
	echo "This is Solaris system."
	SUNOS=1
elif [ "$PLATFORM"="Darwin" ]
then
	echo "This is Mac OSX system."
	MAC=1
elif [ "$PLATFORM"="AIX" ]
then 
	echo "This is AIX system."
	AIX=1
elif [ "$PLATFORM"="Linux" ]
then 
	echo "This is Linux system."
	LINUX=1
else
	echo "Failed to identify this Operating System,Abort!"
	exit 1
fi
echo "Starting to install the software..."
echo 

#exit 0
echo ${JAVA_HOME}
if [ ! -z "${JAVA_HOME-}" ]; then
  echo ${JAVA_HOME}
  case "$(uname -s | tr 'A-Z' 'a-z')" in
    linux)
      JAVA_HOME="$(readlink -f $(which javac) 2>/dev/null | sed 's_/bin/javac__')" || true
      BASHRC="~/.bashrc"
      ;;
    freebsd)
      JAVA_HOME="/usr/local/openjdk8"
      BASHRC="~/.bashrc"
      ;;
    darwin)
      JAVA_HOME="$(/usr/libexec/java_home -v ${JAVA_VERSION}+ 2> /dev/null)" || true
      BASHRC="~/.bash_profile"
      ;;
  esac
fi
if [ ! -x "${JAVA_HOME}/bin/javac" ]; then
  echo >&2
  echo "Java not found, please install the corresponding package" >&2
  echo "See http://bazel.build/docs/install.html for more information on" >&2
  echo "dependencies of Bazel." >&2
  exit 1
fi
if [ ! -x "${JAVA_HOME}/bin/javac" ]; then
  echo ${JAVA_HOME}
  echo >&2
  echo "Java not found, please install the corresponding package" >&2
  echo "See http://bazel.build/docs/install.html for more information on" >&2
  echo "dependencies of Bazel." >&2
  exit 1
fi


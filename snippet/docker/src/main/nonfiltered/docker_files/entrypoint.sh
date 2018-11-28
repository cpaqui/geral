#!/bin/bash

set -e

# Show logo:
source /root/image_info.sh
sed -e "s|@containerImage@|${docker_image_name}|g; s|@buildDate@|${docker_image_build_date}|g" /root/logo.txt

if [ ! -f /.runonce ]; then
  # Check for environment variables:
  if [ -f /root/setupvars.sh ]; then
    source /root/setupvars.sh
  fi

  ## Create /var/log folders if not exist....
  if [ -f /root/logfolders ]; then
    for d in $(cat /root/logfolders) ; do
      if [ ! -d "$d" ]; then
        mkdir -p "$d"
      fi
    done
  fi

  ## Custom start script
  if [ -f /root/custom_start.sh ]; then
    bash /root/custom_start.sh
  fi

  ## Cronjobs:
  DOCKER_CID=$(cat /proc/1/cpuset | cut -c9-)

  HOSTCRONS_PATH=/opt/hostcrons
  if [ -d "${HOSTCRONS_PATH}" ]; then
    echo "Creating cronjobs...."
    shopt -s nullglob
    for i in /root/*.spec ; do
      fname=$(basename $i)
      sed "s|@CID@|${DOCKER_CID}|" $i >"${HOSTCRONS_PATH}/${DOCKER_CID}_${fname}"
    done
  else
    echo "Skipping creation of cronjobs. Mountpoint not found"
  fi

  touch /.runonce
fi

if [ "${1:0:1}" != '-' ]; then
  echo "Executing $@"
  exec "$@"
fi


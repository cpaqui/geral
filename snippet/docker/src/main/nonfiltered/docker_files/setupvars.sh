#!/bin/bash

REQ_ENV_FILE=/root/required_environment
SECRETS_PATH=/run/secrets
if [ -f "${REQ_ENV_FILE}" ]; then
#    echo "Validating enviroment information..."
  notfound=0
  while IFS='=' read -r key value; do
    if [ ! "$key" == "" ] && [[ ! $key =~ ^# ]]; then
      if [ "$value" == "" ]; then
        if [ ! -v "$key" ]; then 
          echo "Env Key: $key not defined"
          notfound=$((notfound+1))
        fi
      else
        if [ -f "${SECRETS_PATH}/${value}" ]; then
          S=${key}
          export "${S}"="$(cat ${SECRETS_PATH}/${value})"
        else
          if [ ! -v "$key" ]; then
            echo "Secret Key: ${value} not available for ${key}"
            notfound=$((notfound+1))
          else
            echo "Secret Key $key overridden with ENV"
          fi
        fi
      fi
    fi
  done < ${REQ_ENV_FILE}
  if [ $notfound -ne 0 ]; then
    echo "$notfound environment variables or secrets required not defined."
    echo "Aborting."
    exit 1
  fi
  echo "------------------------------------------"
  while IFS='=' read -r key value; do
    if [ ! "$key" == "" ] && [[ ! $key =~ ^# ]]; then
      if [ "$value" == "" ]; then
        echo "| $key = ${!key}"
      else
        echo "| $key = **** (Secret)"
      fi
    fi
  done < ${REQ_ENV_FILE}
  echo "------------------------------------------"
else
  echo "required_environment - ${REQ_ENV_FILE} - not found"
  echo "Error exists in building image"
  exit 1
fi

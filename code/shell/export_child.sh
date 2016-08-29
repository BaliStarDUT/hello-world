#!/bin/bash
echo "==============================="
echo "The child is at '${LOCATION:-somewhere not defined}'."
LOCATION=CHINA
echo
echo "The child is at '$LOCATION'."
echo "=========================="
exit 0

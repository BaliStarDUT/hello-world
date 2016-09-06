echo
echo "Find some file in the whole filesystem,take very long time..."
echo "You can press Ctrl+C to abort this..."

find / -name "squid" 2>/dev/null
exit 0

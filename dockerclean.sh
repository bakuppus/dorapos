dockps=$(docker ps | grep -i dorapos- | awk '{print $1}' | tail -n1);
if [ $dockps ];
then
/usr/bin/docker stop $dockps
else
echo "no contianer running"
fi

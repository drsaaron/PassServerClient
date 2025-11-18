#! /bin/sh

# run the service
imageName=$(dockerImageName.sh)
imageVersion=$(getPomAttribute.sh version | sed 's/-RELEASE$//')
containerName=$(echo $imageName | sed -re 's%^.*/([a-zA-Z]*)$%\1%') # could also use basename $(dockerImageName.sh)

socketFile=$(grep socketPath src/main/resources/application.properties | awk -F= '{ print $2 }' )

docker run -it --rm --user $(id -u):$(id -g) -v $socketFile:$socketFile $imageName:$imageVersion

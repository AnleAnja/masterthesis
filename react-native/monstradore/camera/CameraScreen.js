import {Button, Image, StyleSheet, Text, View} from 'react-native';
import {Camera, useCameraDevices} from 'react-native-vision-camera';
import {useEffect, useRef, useState} from 'react';


function CameraScreen() {
    const [permission, setPermission] = useState(null);
    const [photoPath, setPhotoPath] = useState('');
    const camera = useRef(null)

    useEffect(() => {
        async function handlePermission() {
            let cameraPermission = await Camera.getCameraPermissionStatus();
            if (cameraPermission === 'not-determined' || cameraPermission === 'denied') {
                cameraPermission = await Camera.requestCameraPermission();
            }
            setPermission(cameraPermission);
        }

        if (!permission || permission === 'not-determined' || permission === 'denied') {
            handlePermission().catch(console.error);
        }
    }, []);

    const devices = useCameraDevices();
    const device = devices.back;

    const takePhoto = async () => {
        const photo = await camera.current.takePhoto({
            flash: 'off'
        })
        setPhotoPath(`file://${photo.path}`)
    }

    if (!device) {
        return <Text>Waiting for Permission</Text>;
    } else if (photoPath !== '') {
        return <Image source={{uri: photoPath}} style={StyleSheet.absoluteFill} resizeMode="cover"/>
    } else return (
        <View style={styles.container}>
            <Camera
                style={StyleSheet.absoluteFill}
                device={device}
                isActive={true}
                ref={camera}
                photo={true}
            />
            <Button title={"O"} onPress={takePhoto}></Button>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: 'black',
    },
    captureButton: {
        position: 'absolute',
        alignSelf: 'center',
        bottom: 15,
    },
})

export default CameraScreen;



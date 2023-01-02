import { Modal, Image, View, StyleSheet } from 'react-native';
import ImageViewer from 'react-native-image-zoom-viewer';
import exampleImage from '../assets/sample.jpeg';

const exampleImageUri = Image.resolveAssetSource(exampleImage).uri
const images = [{ url: exampleImageUri }];
const renderIndicator = () => '';

function GesturesScreen() {
    return (
        <ImageViewer imageUrls={images} renderIndicator={renderIndicator} style={styles.container} saveToLocalByLongPress={false}/>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignContent: 'center',
    },
});

export default GesturesScreen;

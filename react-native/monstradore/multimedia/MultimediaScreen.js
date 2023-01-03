import {Text, View, StyleSheet, Button} from 'react-native';
import SoundPlayer from 'react-native-sound-player';
import Video from 'react-native-video';

function MultimediaScreen() {
    try {
        SoundPlayer.playSoundFile('sampleaudio', 'mp3');
        SoundPlayer.pause();
    } catch (e) {
        console.log('cannot play the sound file', e);
    }

    return (
        <View style={styles.container}>
            <Text style={styles.headline3}>Audio</Text>
            <View style={styles.audioRow}>
                <Button title="Play" onPress={SoundPlayer.play}/>
                <Button title="Pause" onPress={SoundPlayer.pause}/>
            </View>
            <Text style={styles.headline3}>Video</Text>
            <View style={styles.videoRow}>
                <Video
                    source={{
                        uri: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4',
                    }}
                    style={styles.mediaPlayer}
                    controls={true}
                    resizeMode="cover"
                    hideShutterView={true}
                    paused={true}
                />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignContent: 'center',
        padding: 20,
    },
    headline3: {
        fontSize: 18,
    },
    audioRow: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'flex-start',
    },
    videoRow: {
        flex: 6,
        flexdirection: 'row',
        alignItems: 'flex-start',
    },
    toolbar: {
        marginTop: 30,
        backgroundColor: 'white',
        padding: 10,
        borderRadius: 5,
    },
    mediaPlayer: {
        position: 'absolute',
        top: 0,
        left: 0,
        bottom: 0,
        right: 0,
        height: 250,
    },
});

export default MultimediaScreen;

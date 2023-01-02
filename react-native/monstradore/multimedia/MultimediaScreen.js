import {Text, View, StyleSheet, Button} from 'react-native';
import SoundPlayer from 'react-native-sound-player';
// import React, {useState, useRef} from 'react';
import VideoPlayer from 'react-native-video-player';
// import MediaControls, {PLAYER_STATES} from 'react-native-media-controls';

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
                <Button title="Play" onPress={SoundPlayer.play} />
                <Button title="Pause" onPress={SoundPlayer.pause} />
            </View>
            <View style={styles.videoRow}>
                <Text style={styles.headline3}>Video</Text>
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
        // flexdirection: 'row',
        // alignitems: 'flex-start',
    },
    toolbar: {
        marginTop: 30,
        backgroundColor: 'white',
        padding: 10,
        borderRadius: 5,
    },
    mediaPlayer: {
        height: '100%',
        width: '100%'
    },
});

export default MultimediaScreen;

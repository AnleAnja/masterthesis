import {DocumentDirectoryPath, writeFile, readFile} from 'react-native-fs';
import {Text, TextInput, View, StyleSheet, Button} from 'react-native';
import React from 'react';

function FileAccessScreen() {
    let [text, onChangeText] = React.useState('');
    let [content, setContent] = React.useState('');
    const path = DocumentDirectoryPath + '/samplefile.txt';

    const saveFile = async () => {
        console.log(path);
        console.log(text);
        try {
            await writeFile(path, text, 'utf8');
        } catch (e) {
            console.log('write', e);
        }
    };

    const displayFile = async () => {
        console.log(path);
        try {
            let temp = await readFile(path, 'utf8');
            setContent(temp);
            console.log(content);
        } catch (e) {
            console.log('read', e);
        }
    };

    return (
        <View style={{flex: 1, justifyContent: 'flex-start', direction: 'column'}}>
            <Text style={styles.headline3}>Dateizugriff</Text>
            <Text>Text in Datei schreiben</Text>
            <TextInput
                style={styles.input}
                onChangeText={onChangeText}
                value={text}
                placeholder="Text"
            />
            <View style={styles.row}>
                <Button title="Datei speichern" onPress={saveFile}/>
                <Button title="Datei laden" onPress={displayFile}/>
            </View>
            <Text style={{fontSize: 25}}>{content}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    headline3: {
        fontSize: 18,
    },
    input: {
        height: 40,
        borderWidth: 1,
        padding: 10,
    },
    row: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'flex-start',
    },
});

export default FileAccessScreen;

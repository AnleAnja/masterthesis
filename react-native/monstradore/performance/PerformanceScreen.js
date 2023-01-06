import {Text, View, StyleSheet, TextInput, Button} from 'react-native';
import React from 'react';
import MultiSlider from '@ptomasroos/react-native-multi-slider';

function PerformanceScreen() {
    const [text, onChangeText] = React.useState('20000');
    const [result, setResult] = React.useState(0);
    const [time, setTime] = React.useState(0);
    return (
        <View style={styles.center}>
            <Text style={styles.headline3}>Performance</Text>
            <Text>Die wievielte Primzahl soll berechnet werden?</Text>
            <TextInput
                style={styles.input}
                onChangeText={onChangeText}
                value={text}
            />
            <Button
                title="Berechnen"
                onPress={() => {
                    let start = Date.now();
                    setResult(calcPrime(Number(text)));
                    let end = Date.now();
                    let duration = end - start;
                    setTime(Math.floor(duration / 1000));
                }}
            />
            <Text>Ergebnis: {result}</Text>
            <Text>Benötigte Zeit: {time} Sekunden</Text>
            <MultiSlider />
        </View>
    );
}

function calcPrime(n) {
    let num = 1;
    let count = 0;
    while (count < n) {
        num++;
        let i = 2;
        while (i <= num) {
            if (num % i === 0) {
                break;
            }
            i++;
        }
        if (i === num) {
            count++;
        }
    }
    return num;
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
    center: {
        alignContent: 'center',
        justifyContent: 'center',
        padding: 20,
    },
});

export default PerformanceScreen;

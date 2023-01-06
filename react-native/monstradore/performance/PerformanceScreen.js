import {Button, StyleSheet, Text, TextInput, View} from 'react-native';
import React, {Component, useMemo} from 'react';
import MultiSlider from '@ptomasroos/react-native-multi-slider';
import {WebView} from 'react-native-webview';
import {
    useSharedValue,
    runOnJS,
    runOnUI
} from 'react-native-reanimated';

const html = `
    <html>
        <body>
            <script>
                window.AI = function () {
                    window.postMessage('Hello World')
                }
            </script>
        </body>
    </html>
`

class PerformanceScreen extends Component {
    state = {
        text: '',
        result: 0,
        time: 0
    }
    // const [text, onChangeText] = React.useState('');
    // const [result, setResult] = React.useState(0);
    // //const [n, setN] = React.useState(0);
    // //const result = useMemo(() => calcPrime(n), [n]);
    // const [time, setTime] = React.useState(0);
    // const sharedResult = useSharedValue(0);

    calcPrime(n) {
        'show source';
        console.log('test calc prime');
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
        window.postMessage(num);
    }

    // asyncCalcPrime() {
    //     'worklet';
    //     // let start = Date.now();
    //     runOnJS(calcPrime)(Number(text));
    //     // let end = Date.now();
    //     // let duration = end - start;
    //     // setTime(Math.floor(duration / 1000));
    // }

    handleMessage(e) {
        let message = e.nativeEvent.data;
        console.log(message);
        this.setState({result: Number(message)});
    }

    start() {
        this.WebView.injectJavaScript(this.calcPrime(Number(this.state.text)))
    }

    render() {
        return (
            <View style={styles.center}>
                <Text style={styles.headline3}>Performance</Text>
                <Text>Die wievielte Primzahl soll berechnet werden?</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={txt => this.setState({text: txt})}
                    value={this.state.text}
                />
                <Button
                    title="Berechnen"
                    onPress={() => this.start()}
                />
                <Text>Ergebnis: {this.state.result}</Text>
                <Text>Benötigte Zeit: {this.state.time} Sekunden</Text>
                <MultiSlider/>
                <WebView ref={it => (this.WebView = it)} source={{html: html}} javaScriptEnabled={true}
                         onMessage={this.handleMessage}>
                </WebView>
            </View>
        );
    }

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


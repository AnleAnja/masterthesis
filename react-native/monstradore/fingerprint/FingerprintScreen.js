import {View, Text, Button} from 'react-native';
import {useState, useEffect} from 'react';
import TouchID from "react-native-touch-id";

function FingerprintScreen() {

    const [state, setState] = useState('Authentication required');

    useEffect(() => {
        retryAuth()
    }, []);

    const retryAuth = () => {
        TouchID.isSupported()
            .then(authenticate)
    }

    const authenticate = () => {
        return TouchID.authenticate()
            .then(success => {
                setState('Authentication successful');
            })
            .catch(error => {
                setState('Authentication failed');
            });
    }

    return (
        <View>
            <Text style={{fontSize: 18}}>Fingerabdruck / Face ID</Text>
            <Text>{state}</Text>
            <Button title="Retry" onPress={retryAuth}/>
        </View>
    )
}

export default FingerprintScreen;

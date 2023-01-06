import {ActivityIndicator, Text, View, StyleSheet} from 'react-native';
import {useEffect, useState} from 'react';

function NetworkCallScreen() {
    const [isLoading, setLoading] = useState(true);
    const [data, setData] = useState([]);
    const getData = async () => {
        try {
            const response = await fetch(
                'https://jsonplaceholder.typicode.com/posts/1',
            );
            const json = JSON.stringify(await response.json());
            setData(json);
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        getData();
    }, []);

    return (
        <View>
            <Text style={styles.headline3}>Netzwerkcall</Text>
            {isLoading ? <ActivityIndicator /> : <Text>{data}</Text>}
        </View>
    );
}

const styles = StyleSheet.create({
    headline3: {
        fontSize: 18,
    },
});

export default NetworkCallScreen;

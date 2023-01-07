import Geolocation from '@react-native-community/geolocation';
import {Text, View} from 'react-native';
import {useEffect, useState} from 'react';

function GPSScreen() {

    useEffect(() => {
        Geolocation.requestAuthorization()
    }, []);

    const [lat, setLat] = useState('');
    const [long, setLong] = useState('');
    const location = Geolocation.getCurrentPosition(
        position => {
            setLat(position.coords.latitude.toString());
            setLong(position.coords.longitude.toString());
        },
        error => Alert.alert('Error', JSON.stringify(error)),
        {enableHighAccuracy: true, timeout: 20000, maximumAge: 1000},
    );
    return(
        <View>
            <Text style={{fontSize: 18}}>GPS Sensor</Text>
            <Text>Latitude: {lat}</Text>
            <Text>Longitude: {long}</Text>
        </View>
    )
}

export default GPSScreen;

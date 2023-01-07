import {
    accelerometer,
    setUpdateIntervalForType,
    SensorTypes
} from "react-native-sensors";
import {Text, View} from 'react-native';
import {useEffect, useState} from 'react';
import {map, filter} from "rxjs/operators";

function AccelerationScreen() {

    const [x, setX] = useState('');
    const [y, setY] = useState('');
    const [z, setZ] = useState('');

    useEffect(() => {
        setUpdateIntervalForType(SensorTypes.accelerometer, 400);
    }, []);

    const subscription = accelerometer.subscribe(({x, y, z}) => {
        setX(x.toString());
        setY(y.toString());
        setZ(z.toString());
    });

    return (
        <View>
            <Text style={{fontSize: 18}}>Beschleunigung</Text>
            <Text>Roll: {x}</Text>
            <Text>Pitch: {y}</Text>
            <Text>Yaw: {z}</Text>
        </View>
    )
}

export default AccelerationScreen;

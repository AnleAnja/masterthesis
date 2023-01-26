import SearchBar from 'react-native-platform-searchbar';
import {View, Text} from 'react-native';
import {useRef, useState} from 'react';
import {Picker} from '@react-native-picker/picker';

function IOSScreen() {
    const [value, setValue] = useState('');
    const [selectedLanguage, setSelectedLanguage] = useState();
    const pickerRef = useRef();

    function open() {
        pickerRef.current.focus();
    }

    function close() {
        pickerRef.current.blur();
    }

    return (
        <View>
            <Text style={{fontSize: 18}}>Search Bar</Text>
            <SearchBar value={value} onChangeText={setValue} />
            <Text style={{fontSize: 18}}>Picker</Text>
            <Picker
              ref={pickerRef}
              selectedValue={selectedLanguage}
              onValueChange={itemValue => setSelectedLanguage(itemValue)}>
              <Picker.Item label="Item 1" value="i1" />
              <Picker.Item label="Item 2" value="i2" />
              <Picker.Item label="Item 3" value="i3" />
            </Picker>
        </View>
    );
}

export default IOSScreen;

import {Text, View, StyleSheet, TextInput} from 'react-native';
import React from 'react';

function InputMethodsScreen() {
  const [text1, onChangeText1] = React.useState('');
  const [text2, onChangeText2] = React.useState('');
  return (
    <View style={styles.container}>
      <Text style={styles.headline3}>Texteingabe</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText1}
        value={text1}
        placeholder="Label"
      />
      <Text style={styles.headline3}>Passworteingabe</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText2}
        value={text2}
        secureTextEntry={true}
        placeholder="Password Label"
      />
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
});

export default InputMethodsScreen;

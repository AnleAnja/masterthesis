import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class NetworkCall extends StatefulWidget {
  const NetworkCall({super.key});

  @override
  State<StatefulWidget> createState() => _NetworkCallState();
}

class _NetworkCallState extends State<NetworkCall> {
  late Future<String> futureResponse;

  @override
  void initState() {
    super.initState();
    futureResponse = fetchUrl();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
        body: Column(children: [
      Padding(
        padding: const EdgeInsets.fromLTRB(0, 50, 0, 0),
        child: Text("Netzwerkcall", style: textTheme.headline6),
      ),
      FutureBuilder<String>(
          future: futureResponse,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return Text(snapshot.data!);
            } else if (snapshot.hasError) {
              return const Text('Error');
            }
            return const Text('Loading');
          })
    ]));
  }
}

Future<String> fetchUrl() async {
  final response =
      await http.get(Uri.parse('https://jsonplaceholder.typicode.com/posts/1'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return jsonDecode(response.body);
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load album');
  }
}

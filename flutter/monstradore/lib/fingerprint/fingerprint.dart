import 'package:flutter/material.dart';
import 'package:local_auth/local_auth.dart';
import 'package:flutter/services.dart';
import 'package:local_auth/error_codes.dart' as local_auth_error;

class Fingerprint extends StatefulWidget {
  const Fingerprint({super.key});

  @override
  State<StatefulWidget> createState() => _FingerprintState();
}

class _FingerprintState extends State<Fingerprint> {
  final _localAuthentication = LocalAuthentication();
  bool _isUserAuthorized = false;

  Future<void> authenticateUser() async {
    bool isAuthorized = false;
    try {
      isAuthorized = await _localAuthentication.authenticate(
          localizedReason: "Please authenticate");
    } on PlatformException catch (exception) {
      if (exception.code == local_auth_error.notAvailable ||
          exception.code == local_auth_error.passcodeNotSet ||
          exception.code == local_auth_error.notEnrolled) {
        // Handle this exception here.
      }
    }

    if (!mounted) return;

    setState(() {
      _isUserAuthorized = isAuthorized;
    });
  }

  @override
  void initState() {
    authenticateUser();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
      body: Center(
        child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text("Fingerabdruck / Face ID", style: textTheme.headline6),
          _isUserAuthorized
              ? (const Text("Authentication successful"))
              : (Column(
                  children: [
                    const Text("Authentication failed"),
                    TextButton(
                      onPressed: () => authenticateUser(),
                      child: const Text("Retry"),
                    )
                  ],
                )),
        ],
      )),
    );
  }
}

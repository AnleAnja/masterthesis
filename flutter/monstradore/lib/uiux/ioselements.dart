import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

const List<String> pickerOptions = <String>["Item 1", "Item 2", "Item 3"];

class iOSElements extends StatefulWidget {
  const iOSElements({super.key});

  @override
  State<StatefulWidget> createState() => _iOSElementsState();
}

class _iOSElementsState extends State<iOSElements> {
  late TextEditingController textController;
  int selected = 0;

  @override
  void initState() {
    super.initState();
    textController = TextEditingController(text: 'Search Bar');
  }

  @override
  void dispose() {
    textController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return CupertinoPageScaffold(
        child: Column(children: [
      Text("Search Bar", style: textTheme.headline6),
      CupertinoSearchTextField(
        controller: textController,
        placeholder: 'Search',
      ),
      Text("Picker", style: textTheme.headline6),
      CupertinoButton(
          padding: EdgeInsets.zero,
          onPressed: () => _showDialog(
                CupertinoPicker(
                  magnification: 1.22,
                  squeeze: 1.2,
                  useMagnifier: true,
                  itemExtent: 32.0,
                  onSelectedItemChanged: (int selectedItem) {
                    setState(() {
                      selected = selectedItem;
                    });
                  },
                  children:
                      List<Widget>.generate(pickerOptions.length, (int index) {
                    return Center(
                      child: Text(
                        pickerOptions[index],
                      ),
                    );
                  }),
                ),
              ),
          child: Text(
            pickerOptions[selected],
            style: const TextStyle(
              fontSize: 22.0,
            ),
          )),
    ]));
  }

  void _showDialog(Widget child) {
    showCupertinoModalPopup<void>(
        context: context,
        builder: (BuildContext context) => Container(
              height: 216,
              padding: const EdgeInsets.only(top: 6.0),
              margin: EdgeInsets.only(
                bottom: MediaQuery.of(context).viewInsets.bottom,
              ),
              color: CupertinoColors.systemBackground.resolveFrom(context),
              child: SafeArea(
                top: false,
                child: child,
              ),
            ));
  }
}

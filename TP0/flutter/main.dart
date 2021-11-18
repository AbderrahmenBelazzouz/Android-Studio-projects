import 'package:flutter/material.dart';
// Exercice N°1:
/*
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          brightness: Brightness.dark,
          primaryColor: Colors.blueGrey
      ),
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                  child: Text("Hello World!"),
              ),
              Container(
                child: ElevatedButton(
                  child: Text("OK"),
                  onPressed: (){},
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
*/




// Exercice N°2:

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          brightness: Brightness.dark,
          primaryColor: Colors.blueGrey
      ),
      home: Builder(
        builder: (context) => Center(
          child: ElevatedButton(
            child: Text("OK"),
            onPressed: (){
              Navigator.push(context, MaterialPageRoute(builder: (context) => MyActivity2()),
              );
            },
          ),
        ),
      ),
    );
  }
}

class MyActivity2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          brightness: Brightness.dark,
          primaryColor: Colors.blueGrey
      ),
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                child: Text("Activity 2"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

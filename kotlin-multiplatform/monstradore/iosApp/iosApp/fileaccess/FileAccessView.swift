//
//  FileAccessView.swift
//  iosApp
//
//  Created by Anja on 25.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct FileAccessView: View {
    @State private var fileContentIn: String = ""
    @State private var fileContentOut: String = ""
    @State private var pathWithFilename = URL(fileURLWithPath: "")
    var body: some View {
        VStack {
            Text("Dateizugriff")
                .font(.headline)
            Text("Text in Datei schreiben")
            TextField("Text", text: $fileContentIn)
            HStack {
                Button(action: {
                    if let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first {
                        pathWithFilename = documentDirectory.appendingPathComponent("samplefile.txt")
                        do {
                            try fileContentIn.write(to: pathWithFilename, atomically: true, encoding: .utf8)
                        } catch {
                            print("Fehler beim Schreiben")
                        }
                    }
                }) {
                    Text("Datei speichern")
                }
                Button(action: {
                    do {
                        fileContentOut = try String(contentsOf: pathWithFilename)
                    } catch {
                        print("Fehler beim Lesen")
                    }
                }) {
                    Text("Datei laden")
                }
            }
            Text(fileContentOut)
        }
        .padding()
    }
}

//struct FileAccessView_Previews: PreviewProvider {
//    static var previews: some View {
//        FileAccessView()
//    }
//}

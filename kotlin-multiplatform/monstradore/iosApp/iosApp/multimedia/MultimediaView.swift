//
//  MultimediaView.swift
//  iosApp
//
//  Created by Anja on 23.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import AVKit

struct MultimediaView: View {
    @State var audioPlayer: AVAudioPlayer!
        var body: some View {
                VStack {
                    Text("Audio")
                        .font(.headline)
                        .multilineTextAlignment(.leading)
                    HStack {
                        Button(action: {
                            self.audioPlayer.play()
                        }) {
                            Image(systemName: "play")
                        }
                        Button(action: {
                            self.audioPlayer.pause()
                        }) {
                            Image(systemName: "pause")
                        }
                    }
                    Text("Video")
                        .font(.headline)
                        .multilineTextAlignment(.leading)
                }
            .onAppear {
                let sound = Bundle.main.path(forResource: "sample", ofType: "mp3")
                self.audioPlayer = try! AVAudioPlayer(contentsOf: URL(fileURLWithPath: sound!))
            }
        }

}

//struct MultimediaView_Previews: PreviewProvider {
//    static var previews: some View {
//        MultimediaView()
//    }
//}

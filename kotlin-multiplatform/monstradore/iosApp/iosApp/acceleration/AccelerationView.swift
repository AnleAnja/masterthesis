//
//  AccelerationView.swift
//  iosApp
//
//  Created by Anja on 29.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import CoreMotion

struct AccelerationView: View {
    let motionManager = CMMotionManager()
    let queue = OperationQueue()

    @State private var pitch = Double.zero
    @State private var yaw = Double.zero
    @State private var roll = Double.zero
    var body: some View {
        VStack{
            Text("Beschleunigung")
                .font(.headline)
            Text("Pitch: \(pitch)")
            Text("Yaw: \(yaw)")
            Text("Roll: \(roll)")
        }
        .onAppear {
                self.motionManager.startDeviceMotionUpdates(to: self.queue) { (data: CMDeviceMotion?, error: Error?) in
                    guard let data = data else {
                        print("Error: \(error!)")
                        return
                    }
                    let attitude: CMAttitude = data.attitude

                    DispatchQueue.main.async {
                        self.pitch = attitude.pitch
                        self.yaw = attitude.yaw
                        self.roll = attitude.roll
                    }
                }
            }
    }
}

struct AccelerationView_Previews: PreviewProvider {
    static var previews: some View {
        AccelerationView()
    }
}

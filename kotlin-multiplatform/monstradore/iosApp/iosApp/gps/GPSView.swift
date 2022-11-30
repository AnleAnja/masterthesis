//
//  GPSView.swift
//  iosApp
//
//  Created by Anja on 29.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct GPSView: View {
    @StateObject var locationManager = LocationManager()
    
    var userLatitude: String {
        return "\(locationManager.lastLocation?.coordinate.latitude ?? 0)"
    }
    
    var userLongitude: String {
        return "\(locationManager.lastLocation?.coordinate.longitude ?? 0)"
    }
    
    var body: some View {
        VStack {
            Text("Location")
                .font(.headline)
            HStack {
                Text("Latitude: \(userLatitude), Longitude \(userLongitude)")
            }
        }
    }
}

//struct GPSView_Previews: PreviewProvider {
//    static var previews: some View {
//        GPSView()
//    }
//}

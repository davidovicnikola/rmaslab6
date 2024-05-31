package com.example.lv5

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PoiApplication: Application() {
    val db by lazy {Firebase.firestore}
}
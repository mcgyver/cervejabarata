package br.com.devnull.cervejabarata.models

import android.net.Uri
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class User(
        @PrimaryKey var id: Long = 0,
        var name: String = "",
        var photoUrl: String = "",
        var email: String = ""
) : RealmObject() {}
package br.com.devnull.cervejabarata

import android.app.Application
import io.realm.Realm


class CervejaBarataApplication : Application() {
    override fun onCreate() {

        super.onCreate()
        Realm.init(this)
        /*val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)*/

    }

}
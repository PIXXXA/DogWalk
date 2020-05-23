package com.firsttask.dog.di

import com.firsttask.dog.db.dao.*
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.addpet.NewPetFragment
import com.firsttask.dog.fragments.editprofile.EditProfileFragment
import com.firsttask.dog.fragments.login.LoginFragment
import com.firsttask.dog.fragments.profile.ProfileFragment
import com.firsttask.dog.fragments.profile.ProfileViewModelFactory
import com.firsttask.dog.fragments.registration.RegistrationFragment
import com.firsttask.dog.fragments.searchresult.SearchFragment
import com.firsttask.dog.fragments.walkerslist.WalkerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class, ViewModelFactoryModule::class, RoomModule::class])
interface AppComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(walkersFragment: WalkerFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(editProfileFragment: EditProfileFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(newPetFragment: NewPetFragment)

    var userDao: UserDao
    var walkerDao: WalkerDao
    var orderDao: OrderDao
    var ownerDao: OwnerDao
    var petDao: PetDao
    var announcementDao : AnnouncementDao
    var appDatabase: AppDatabase
}
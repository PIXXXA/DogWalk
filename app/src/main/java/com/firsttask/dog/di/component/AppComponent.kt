package com.firsttask.dog.di.component

import com.firsttask.dog.db.dao.*
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.di.AppModule
import com.firsttask.dog.di.RoomModule
import com.firsttask.dog.di.UtilsModule
import com.firsttask.dog.di.ViewModelFactoryModule
import com.firsttask.dog.fragments.addpet.NewPetFragment
import com.firsttask.dog.fragments.editpet.EditPetFragment
import com.firsttask.dog.fragments.editprofile.EditProfileFragment
import com.firsttask.dog.fragments.login.LoginFragment
import com.firsttask.dog.fragments.order.OrderFragment
import com.firsttask.dog.fragments.profile.ProfileFragment
import com.firsttask.dog.fragments.registration.RegistrationFragment
import com.firsttask.dog.fragments.searchresult.SearchFragment
import com.firsttask.dog.fragments.announcementlist.AnnouncementFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class, ViewModelFactoryModule::class, RoomModule::class])
interface AppComponent {

    fun inject(registrationFragment: RegistrationFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(walkersFragment: AnnouncementFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(editProfileFragment: EditProfileFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(newPetFragment: NewPetFragment)
    fun inject(editPetFragment: EditPetFragment)
    fun inject(orderFragment: OrderFragment)

    var userDao: UserDao
    var walkerDao: WalkerDao
    var orderDao: OrderDao
    var ownerDao: OwnerDao
    var petDao: PetDao
    var announcementDao: AnnouncementDao
    var appDatabase: AppDatabase
}
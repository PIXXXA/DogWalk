package com.firsttask.dog.di

import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.addpet.NewPetViewModelFactory
import com.firsttask.dog.fragments.editpet.EditPetViewModelFactory
import com.firsttask.dog.fragments.editprofile.EditProfileFactory
import com.firsttask.dog.fragments.login.LoginViewModelFactory
import com.firsttask.dog.fragments.order.OrderViewModelFactory
import com.firsttask.dog.fragments.profile.ProfileViewModelFactory
import com.firsttask.dog.fragments.registration.RegistrationFactory
import com.firsttask.dog.fragments.searchresult.SearchViewModelFactory
import com.firsttask.dog.fragments.announcementlist.AnnouncementViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun getRegistrationViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): RegistrationFactory {
        return RegistrationFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getLoginViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): LoginViewModelFactory {
        return LoginViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getWalkersViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): AnnouncementViewModelFactory {
        return AnnouncementViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getSearchViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): SearchViewModelFactory {
        return SearchViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getEditProfileViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): EditProfileFactory {
        return EditProfileFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getProfileViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getNewPetViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): NewPetViewModelFactory {
        return NewPetViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getEditPetViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): EditPetViewModelFactory {
        return EditPetViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getOrderViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): OrderViewModelFactory {
        return OrderViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }
}
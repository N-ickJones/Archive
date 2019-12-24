# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.conf import settings
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render, redirect
from mainApp.forms import SignUpForm
from django.core.mail import send_mail

# Create your views here.


def signup(request):
    if request.method == 'POST':
        form = request.POST
        # if form.is_valid():
        #     save_it = form.save()
        #     save_it.save()
        #     username = form.cleaned_data.get('username')
        #     raw_password = form.cleaned_data.get('password1')
        #     user = authenticate(username=username, password=raw_password)
        #     login(request, user)
        #     # Start Email
        #     subject = 'Thanks for Signing Up!'
        #     message = 'Welcome! At Code Blood we appreciate your commitment to Saving Lives. '
        #     from_email = settings.EMAIL_HOST_USER
        #     to_list = [save_it.email, settings.EMAIL_HOST_USER]
        #     send_mail(subject, message, from_email, to_list, fail_silently=True)
        #     # End Email

        search = form.get('search_bar')
        
        print(search)
        return redirect('home')


    else:
        form = SignUpForm()
    return render(request, 'registration/signup.html', {'form': form})


def logout(request):

    return render(request, 'registration/logged_out.html')


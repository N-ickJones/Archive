# -*- coding: utf-8 -*-
from __future__ import unicode_literals

# import re
# import time
from .forms import *
from .tokens import account_activation_token
from core_settings import settings
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import User
# from django.contrib.messages import constants as messages
from django.contrib.sites.shortcuts import get_current_site
from django.core.mail import send_mail
# from django.db import IntegrityError
# from django.http import HttpResponse
# from django.http import HttpResponseRedirect
# from django.views import View
from django.shortcuts import render, redirect
from django.template.loader import render_to_string
from django.utils import timezone
from django.utils.encoding import force_bytes, force_text
from django.utils.http import urlsafe_base64_encode, urlsafe_base64_decode
from core_app.models import Attendance
from django.core.files import File
from django.core.files.storage import default_storage
from core_app.Periodic.database.databaseQuery import DBQuery


# Page Settings
website_name = 'Pettus & Jones Family Reunion'
page_base = 'Base-Content/base.html'
page_content = {
    'website_name': website_name,
    'submenu_top': 'SubMenu/default_top.html',
    'submenu_bottom': 'SubMenu/default_bottom.html',
    'content_top': 'Base-Content/base_content_top.html',
    'content_bottom': '',
    'login_form': LoginForm(),
    'search_form': SearchForm(),
}
# Email Settings
mail = True
website_email = website_name + '@mail.com'


def index(request):
    page_content['content_top'] = 'Base-Content/base_content_top.html'
    page_content['content_bottom'] = ''
    return render(request, page_base, page_content)


def login_view(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            user = authenticate(username=form.cleaned_data['username'], password=form.cleaned_data['password'])
            if user is not None:
                login(request, user)
                page_content['login_alert'] = ''
                return redirect(request.POST.get('next'))
            else:
                page_content['login_alert'] = 'Invalid Credentials'
                return redirect(request.POST.get('next'))
        else:
            page_content['login_alert'] = 'Invalid Credentials'
            return redirect(request.POST.get('next'))
    else:
        return redirect('/')


def logout_view(request):
    if request.method == 'POST':
        form = request.POST
        logout(request)
        # return redirect(form.get('next'))
        return redirect('/')
    else:
        return redirect('/')


def signup_view(request):
    if request.method == 'POST':
        form = SignupForm(request.POST)
        if form.is_valid():
            user = User.objects.create_user(form.cleaned_data['username'], form.cleaned_data['email'],
                                            form.cleaned_data['password1'])
            user.first_name = form.cleaned_data['first_name']
            user.last_name = form.cleaned_data['last_name']
            user.last_login = timezone.now()
            user.is_superuser = False
            user.is_active = False  # Cannot Login Until Email Verification
            user.is_staff = False
            user.save()
            # Start Email
            current_site = get_current_site(request)
            subject = 'Activate your ' + website_name + ' account.'
            message = render_to_string('registration/email_confirm.html', {
                'user': user,
                'domain': current_site.domain,
                'uid': urlsafe_base64_encode(force_bytes(user.pk)).decode(),
                'token': account_activation_token.make_token(user),
            })
            from_email = website_email
            to_list = [form.cleaned_data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            # End Email
            page_content['content_top'] = 'registration/email_check.html'
            page_content['content_bottom'] = ''
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'registration/default_form.html'
            page_content['form'] = form
            page_content['form_title'] = '{} Account Form'.format(website_name)
            return render(request, page_base, page_content)
    elif request.method == 'GET':
        form = SignupForm()
        page_content['content_top'] = 'registration/default_form.html'
        page_content['form'] = form
        page_content['form_title'] = '{} Account Form'.format(website_name)
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Signup_view')


def activate(request, uidb64,  token):
    try:
        uid = force_text(urlsafe_base64_decode(uidb64))
        user = User.objects.get(pk=uid)
    except(TypeError, ValueError, OverflowError, User.DoesNotExist):
        user = None
    if user is not None and account_activation_token.check_token(user, token):
        user.is_active = True
        user.save()
        login(request, user)
        page_content['content_top'] = 'registration/email_confirm_complete.html'
        page_content['content_bottom'] = ''
        return redirect('email_confirm_complete')
    else:
        page_content['content_top'] = 'registration/invalid_activation_code.html'
        return render(request, page_base, page_content)


def email_confirm_complete(request):
    page_content['content_top'] = 'registration/email_confirm_complete.html'
    page_content['content_bottom'] = ''
    return render(request, page_base, page_content)


def username_recovery(request):
    if request.method == 'POST':
        form = UsernameRecoveryForm(request.POST)
        if form.is_valid():
            user = User.objects.get(email=form.data['email'])
            current_site = get_current_site(request)
            subject = 'Activate your ' + website_name + ' account.'
            message = render_to_string('registration/email_username_recovery.html', {
                'user': user,
                'domain': current_site.domain,
            })
            from_email = website_email
            to_list = [form.data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            page_content['content_top'] = 'registration/username_sent.html'
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'registration/default_form.html'
            page_content['form'] = form
            page_content['form_title'] = '{} Account Form'.format(website_name)
            page_content['username_form_error'] = 'Invalid Email'
            return render(request, page_base, page_content)
    elif request.method == 'GET':
        form = UsernameRecoveryForm()
        page_content['content_top'] = 'registration/default_form.html'
        page_content['form'] = form
        page_content['form_title'] = 'Username Recovery'
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Username Recovery')


def password_reset_form(request):
    if request.method == 'POST':
        form = PasswordRecoveryForm(request.POST)
        if form.is_valid():
            user = User.objects.get(email=form.data['email'])
            current_site = get_current_site(request)
            subject = website_name + ' Password Recovery'
            message = render_to_string('registration/password_reset_email.html', {
                'user': user,
                'protocol': 'http',
                'domain': current_site.domain,
                'uid': urlsafe_base64_encode(force_bytes(user.pk)).decode(),
                'token': account_activation_token.make_token(user),
            })
            from_email = website_email
            to_list = [form.data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            page_content['content_top'] = 'registration/password_reset_done.html'
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'registration/default_form.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Recovery'
            page_content['username_form_error'] = 'Invalid Email'
            return render(request, page_base, page_content)

    elif request.method == 'GET':
        form = PasswordRecoveryForm()
        page_content['content_top'] = 'registration/default_form.html'
        page_content['form'] = form
        page_content['form_title'] = 'Password Recovery'
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Password Recovery')


def password_reset_confirm(request, uidb64,  token):
    if request.method == 'POST':
        form = PasswordResetForm()
        if form.is_valid:
            try:
                uid = force_text(urlsafe_base64_decode(uidb64))
                user = User.objects.get(pk=uid)
            except(TypeError, ValueError, OverflowError, User.DoesNotExist):
                user = None
            if user is not None:
                user.set_password(form.cleaned_data['password2'])
                user.save()
                return redirect('/password_reset_complete')
            else:
                return redirect('/invalid_activation_code')
        else:
            page_content['content_top'] = 'registration/default_form.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Recovery'
            return render(request, page_base, page_content)
    if request.method == 'GET':
        try:
            uid = force_text(urlsafe_base64_decode(uidb64))
            user = User.objects.get(pk=uid)
        except(TypeError, ValueError, OverflowError, User.DoesNotExist):
            user = None
        if user is not None and account_activation_token.check_token(user, token):
            form = PasswordResetForm()
            page_content['email'] = user.email
            page_content['content_top'] = 'registration/default_form.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Reset'
            return render(request, page_base, page_content)
        else:
            return redirect('/invalid_activation_code')
    else:
        print('Configure a ' + request.method + ' Request Method in Password Reset Confirm')


def password_reset_complete(request):
    page_content['content_top'] = 'registration/password_reset_complete.html'
    return render(request, page_base, page_content)


def password_change_form(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            form = PasswordChangeForm(request.POST)
            if form.is_valid():
                user = authenticate(username=request.user, password=form.cleaned_data['password1'])
                if user is not None:
                    user.set_password(form.cleaned_data['password3'])
                    user.save()
                    login(request, user)
                    return redirect('/password_change_complete')
                else:
                    form.add_error('password1', "Invalid Password")
                    page_content['content_top'] = 'registration/default_form.html'
                    page_content['form'] = form
                    page_content['form_title'] = 'Password Recovery'
                    return render(request, page_base, page_content)
            else:
                page_content['content_top'] = 'registration/default_form.html'
                page_content['form'] = form
                page_content['form_title'] = 'Password Recovery'
                return render(request, page_base, page_content)
        elif request.method == 'GET':
            if request.user.is_authenticated:
                form = PasswordChangeForm()
                page_content['content_top'] = 'registration/default_form.html'
                page_content['form'] = form
                page_content['form_title'] = 'Password Change'
                return render(request, page_base, page_content)
            else:
                return redirect('/')
        else:
            print('Configure a ' + request.method + ' Request Method in Password Change Form')
    else:
        return redirect('/')


def password_change_complete(request):
    page_content['content_top'] = 'registration/password_change_complete.html'
    return render(request, page_base, page_content)


def profile_view(request):

    if request.user.is_authenticated:
        if request.method == 'POST':
            return redirect('/profile/')

        elif request.method == 'GET':
            form = ProfileForm()
            page_content['content_top'] = 'registration/profile.html'
            page_content['form'] = form
            page_content['form_title'] = '{} User Profile'.format(website_name)
            return render(request, page_base, page_content)
    else:
        return redirect('/')


def setting_view(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            return render(request, page_base, page_content)
        elif request.method == 'GET':
            form = SettingsForm()
            page_content['content_top'] = 'registration/settings_form.html'
            page_content['form'] = form
            page_content['form_title'] = '{} User Settings'.format(website_name)
            return render(request, page_base, page_content)
    else:
        return redirect('/')

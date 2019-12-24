# -*- coding: utf-8 -*-
from __future__ import unicode_literals
# from django.core.mail import send_mail
from django.db import models


# Create your models here.


class Attendance(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    zip_code = models.CharField(max_length=100)
    adult_count = models.IntegerField()
    child_count = models.IntegerField()


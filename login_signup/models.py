from django.db import models

# Create your models here.
class User(models.Model):
    username = models.CharField(max_length=1000)
    password = models.CharField(max_length=500)
    token = models.CharField(max_length=1000)
    artist = models.CharField(max_length=100)
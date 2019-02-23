from django.shortcuts import render
from django.contrib.auth import authenticate
from django.contrib.auth.models import User
from django.http import JsonResponse
# from django.contrib.auth import authenticate, login, logout
import json
# Create your views here.
def index(request):
    return JsonResponse({'name': 'dev'})


def login(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        username = data['username']
        password = data['password']
        user = User.objects.filter(username=username,password=password)
        print(user)
        if user:
            return JsonResponse({'msg':'successfuly login'},safe=False)
        else:
            return JsonResponse({'msg':'no_user'},safe = False)
    else:
        JsonResponse({'msg':'wrong method'})


def register(request):
    if request.method == 'POST':
        data  = json.loads(request.body)
        username = data['username']
        password = data['password']
        if username:
            create = User.objects.create(username = username,password = password)
            return JsonResponse({'msg':'succesfully created'},safe =False,status=201)

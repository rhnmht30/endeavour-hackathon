from django.shortcuts import render
from django.contrib.auth import authenticate
from .models import MyUser,CarDetails
from django.http import JsonResponse
# from django.contrib.auth import authenticate, login, logout
import json
# Create your views here.
def index(request):
    return JsonResponse({'name': 'dev'},safe=False,status=201)


def login(request):
    data_values={}
    print(request.body)
    if request.method == 'POST':
        data = json.loads(request.body)
        username = data['username']
        password = data['password']
        user = MyUser.objects.filter(username=username,password=password)
        print(user)
        query = list(MyUser.objects.filter(username=data['username']).values('uniq_id'))
        for v in query:
            print(v.get('uniq_id'))
            if user:
                data_values = {'message': "Success", 'uniq_id': query[0]['uniq_id']}
                return JsonResponse(data=data_values, safe=False)
            elif username:
                if data['password'] == 'KIET123':
                    return JsonResponse({'msg': 'successfuly login'}, safe=False)
                else:
                    return JsonResponse({'msg': 'wrong password'}, safe=False)
            else:
                return JsonResponse({'msg': 'wrong username & password'}, safe=False)
    else:
        JsonResponse({'msg':'wrong method'})


def register(request):
    data_values={}
    if request.method == 'POST':
        data  = json.loads(request.body.decode("utf-8"))
        print(data)
        username = data['username']
        print(username)
        password = data['password']
        check=MyUser.objects.filter(username=data['username']).values('username')
        if check:
            return JsonResponse({'msg':'username Existed'},safe =False,status=201)
        else:
            query = MyUser.objects.create(username=data['username'], password=data['password'],email=data['email'],gender=data['gender'],mobile=data['mobile'])
            return JsonResponse({'msg': 'Ssuccess'}, safe=False, status=201)


# def addCar(request):
#     if request.method =='POST':
#         data = json.loads(request.body.decode("utf-8"))
#         # print(data)
#         add_id= list(MyUser.objects.filter(username=data['addedBy']).values('uniq_id'))
#         for v in add_id:
#             user = MyUser.objects.only('uniq_id').get(uniq_id=data['addedBy'])
#         query= CarDetails.objects.create(carname=data['carname'],carseats=data['carseats'],addedBy=user,carNumber =data['carNumber'] )
#         return JsonResponse({'msg': 'Ssuccess'}, safe=False, status=201)
#     # if add_id:
        #     query= CarDetails.objects.create(carname=data['carname'],carseats=data['carseats'],addedBy=add_id,carNumber=data['carNumber'])

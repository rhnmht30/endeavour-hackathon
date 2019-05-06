from django.shortcuts import render
from django.contrib.auth import authenticate
from .models import MyUser,CarDetails,startPooling,userPooling
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
        if user:
            query = list(MyUser.objects.filter(username=data['username']).values('uniq_id'))
            for v in query:
                print(v.get('uniq_id'))
                if user:
                    data_values = {'message': "Success", 'uniq_id': query[0]['uniq_id']}
                    return JsonResponse(data=data_values, safe=False)
                
        else:
            return JsonResponse({'message': 'wrong username or password'}, safe=False)
    else:
        JsonResponse({'message':'wrong method'})


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
            return JsonResponse({'message':'username Existed'},safe =False,status=201)
        else:
            query = MyUser.objects.create(name=data['name'],username=data['username'], password=data['password'],email=data['email'],gender=data['gender'],mobile=data['mobile'])
            return JsonResponse({'message': 'successfully Inserted'}, safe=False, status=201)


def addCar(request):
    if request.method =='POST':
        print(request.body)
        data = json.loads(request.body.decode("utf-8"))
        user = MyUser.objects.get(uniq_id=data['addedBy'])

        # carnumber = CarDetails.object
        if user:
            query= CarDetails.objects.create(carname=data['carname'],carseats=data['carseats'],addedBy=user,carNumber =data['carnumber'] )
            if query:
                return JsonResponse({'message': 'Success'}, safe=False, status=201)
            else:
                return JsonResponse({'message': 'some error occured'}, safe=False, status=400)
        else:
            return JsonResponse({'message': 'User Doesnot Exist'}, safe=False, status=400)


def addPool(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode("utf-8"))
        carnumber = CarDetails.objects.filter(carNumber=data['carNumber']).values('cardetailsId')
        if carnumber:
            print(carnumber.get('cardetailsId'))
            myCar = CarDetails.objects.get(cardetailsId=carnumber.get('cardetailsId'))
            # seat = list(CarDetails.objects.filter(carNumber=data['carNumber']).values('carseats'))
            # print(seat)
            if myCar:
                query = startPooling.objects.create(time_start=data['time_start'],time_end = data['time_end'],latitude=data['latitude'],longitude=data['longitude'],status='ACTIVE',carPooled=myCar,seats=data['carseats'])
                query2 = list(startPooling.objects.filter(carPooled=myCar).values('latitude','longitude'))
                print(query2)
                return JsonResponse({'message': 'Success'}, safe=False, status=201)

def profile(request):
    data_values = {}
    cars =[]
    if request.method =='GET':
        query = MyUser.objects.filter(uniq_id=request.GET['uniq_id']).values('name','username','password','gender','email','mobile')
        query2 = list(CarDetails.objects.filter(addedBy_id=request.GET['uniq_id']).values('carname','carseats','carNumber'))
        print(query)
        if list(query):
            cars.append({'userdata':query[0]})
            # return JsonResponse(data=data_values, safe=False, status=201)
        for q in query2:
            cars.append({'car':q})
        return JsonResponse(data=cars, safe=False, status=201)

def getPoolDetails(request):
    data_values = {}
    pools = []
    if request.method=='GET':
        query = list(startPooling.objects.filter(status="ACTIVE").values('time_start','time_end','latitude','longitude','carPooled_id'))
        # print(query)
        for q in query:
            car=q.get('carPooled_id')
            cardeatils = list(CarDetails.objects.filter(cardetailsId=car).values('carname','carseats','carNumber','addedBy'))
            # pools.append({'pooling': q,'cardetails':list(cardeatils)})
            for m in cardeatils:
                userdetails = list(MyUser.objects.filter(uniq_id=cardeatils[0].get('addedBy')).values('name','mobile','email'))
                for x in userdetails:
                    pools.append({'pooling': q, 'cardetails': m,'poolerdetails':x})
                    print(pools)
            # print(q.get('carPooled_id'))
            # print(carpooled)
        return JsonResponse(data=pools,safe=False,status=200)
    else:
        return JsonResponse({'message': 'errro'}, safe=False, status=500)

def addUsertoPool(request):
    if request.method=='POST':
        data = json.loads(request.body.decode("utf-8"))
        print(data)
        uniq = data['uniq_id']
        query_search = list(MyUser.objects.filter(uniq_id=uniq).values('uniq_id'))
        # print(query_search.get('uniq_id'))
        if query_search:
            query_car_id = list(CarDetails.objects.filter(carNumber=data['carNumber']).values('cardetailsId'))
            if query_car_id:
                query_search_seats = list(startPooling.objects.filter(carPooled=query_car_id[0].get('cardetailsId')).values('seats','startPoolingId'))
                print(query_search_seats[0].get('seats'))
                if query_search_seats[0].get('seats')>0:
                    try:
                        search_already_pooled_once = userPooling.objects.filter(userPooled = data['uniq_id'] ).values('status')
                        if search_already_pooled_once[0].get('status') == 'ACTIVE':
                            return  JsonResponse({'message': 'User Already Pooled'}, safe=False, status=400)
                        elif search_already_pooled_once[0].get('status') == 'INACTIVE':
                            poolingwith = startPooling.objects.get(carPooled=query_car_id[0].get('cardetailsId'))
                            print("checkkkkk")
                            userPooling.objects.filter(userPooled=data['uniq_id']).update(status = 'ACTIVE',poolingwithid=poolingwith)
                            return  JsonResponse({'message': 'User Successfully Updated'}, safe=False, status=200)
                    except:
                        add = MyUser.objects.get(uniq_id=uniq)
                        poolingwith = startPooling.objects.get(carPooled=query_car_id[0].get('cardetailsId'))
                        userPooling.objects.create(status='ACTIVE',userPooled=add,poolingwithid = poolingwith)
                        return  JsonResponse({'message': 'User Successfully Pooled'}, safe=False, status=200)
                    
                return JsonResponse({'message': 'Seats Fulll'}, safe=False, status=400)
        else:
            return JsonResponse({'message': 'User Not found'}, safe=False, status=400)
    else:
        return JsonResponse({'message': 'Wrong Request type'}, safe=False, status=400)

def getcurrentPools(request):
    data_values = {}
    if request.method=='GET':
        query = list(userPooling.objects.filter(userPooled=request.GET['user_id']).values('status'))
        if len(query)!=0:
            data_values = {'status': query[0].get('status')}
            return JsonResponse(data=data_values, safe=False, status=200)
        else:
            cardetailid = CarDetails.objects.get(addedBy=request.GET['user_id'])
            print("hello")
            query3 = list(startPooling.objects.filter(carPooled=cardetailid).values('status'))
            if len(query3)!=0:
                print(query3)
                data_values = {'status': query3[0].get('status')}
                return JsonResponse(data=data_values, safe=False, status=200)
        # else:
        #     data_values = {'status': 'INACTIVE'}
        #     return JsonResponse(data=data_values, safe=False, status=200)

def endpool(request):
    if request.method=='GET':
        query = userPooling.objects.filter(userPooled=request.GET['user_id']).update(status = 'INACTIVE')
        if query:
            return JsonResponse({'message': 'Successfully Endpool'}, safe=False, status=200)
        else:
            return JsonResponse({'message': ' Some error occured'}, safe=False, status=401)
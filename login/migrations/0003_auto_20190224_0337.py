# Generated by Django 2.1.7 on 2019-02-23 22:07

from decimal import Decimal
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('login', '0002_auto_20190224_0259'),
    ]

    operations = [
        migrations.AddField(
            model_name='startpooling',
            name='latitude',
            field=models.DecimalField(decimal_places=10, default=Decimal('0.0000'), max_digits=30),
        ),
        migrations.AddField(
            model_name='startpooling',
            name='longitude',
            field=models.DecimalField(decimal_places=10, default=Decimal('0.0000'), max_digits=30),
        ),
        migrations.AddField(
            model_name='startpooling',
            name='status',
            field=models.CharField(max_length=100, null=True),
        ),
        migrations.AddField(
            model_name='startpooling',
            name='time_end',
            field=models.BigIntegerField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='startpooling',
            name='time_start',
            field=models.BigIntegerField(null=True),
        ),
    ]

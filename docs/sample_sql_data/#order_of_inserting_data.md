# order of inserting the data
1. car_features.sql
2. car.sql
3. car_pricing.sql
4. user.sql
5. address.sql
    1. ensure that **id** in **address** table starts from **31**
6. car_listing.sql

# useful commands
- to get host and its addresses to add car-listings

```sql

 select user.id, role_enum , address.id as address_id  from user, address 
 where user.id = address.user_id and role_enum = 'HOST';

+----+-----------+------------+
| id | role_enum | address_id |
+----+-----------+------------+
|  7 | HOST      |         21 |
|  7 | HOST      |         22 |
|  7 | HOST      |         23 |
|  8 | HOST      |         24 |
|  8 | HOST      |         25 |
|  8 | HOST      |         26 |
|  9 | HOST      |         27 |
|  9 | HOST      |         28 |
|  9 | HOST      |         29 |
+----+-----------+------------+
9 rows in set (0.00 sec)

mysql> select id car_Id from car;

+--------+
| car_Id |
+--------+
|      1 |
|      2 |
|      3 |
|      4 |
|      5 |
|      6 |
|      7 |
|      8 |
|      9 |
|     10 |
+--------+
10 rows in set (0.00 sec)

```
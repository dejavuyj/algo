select t.request_at as 'Day',
       ROUND(SUM(IF(t.status = 'completed', 0, 1)) / COUNT(t.status), 2) as 'Cancellation Rate'
from Trips as t
         join Users AS U1 ON (t.client_id = U1.users_id and U1.banned = 'No')
         join Users AS U2 ON (t.driver_id = U2.users_id and U2.banned = 'No')
where t.request_at between '2013-10-01' and '2013-10-03'
group by t.request_at;
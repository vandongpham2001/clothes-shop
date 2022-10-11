SELECT * FROM `clothes-shop`.categories 
where `clothes-shop`.categories.id in (select parent_id from categories)
update `clothes-shop`.products set `clothes-shop`.products.status = 1 where `clothes-shop`.products.id >0
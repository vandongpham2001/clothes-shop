SELECT * FROM `clothes-shop`.categories 
where `clothes-shop`.categories.id in (select parent_id from categories)
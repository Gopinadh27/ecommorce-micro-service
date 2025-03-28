-- Insert into t_category
INSERT INTO t_category (name, description) VALUES
('Keyboards', 'Computer Keyboards'),
('Monitors', 'Computer Monitors'),
('Screens', 'Display Screens'),
('Mice', 'Computer Mice'),
('Accessories', 'Computer Accessories');

-- Insert into t_product
INSERT INTO t_product (name, description, available_quantity, price, category_id) VALUES
('Mechanical Keyboard', 'Mechanical Keyboard', 10, 99.99, (SELECT id FROM t_category WHERE name = 'Keyboards')),
('Wireless Compact Keyboard', 'Wireless Compact Keyboard', 15, 79.99, (SELECT id FROM t_category WHERE name = 'Keyboards')),
('Gaming Keyboard 1', 'Backlit Gaming Keyboard', 20, 129.99, (SELECT id FROM t_category WHERE name = 'Keyboards')),
('Ergonomic Keyboard 1', 'Mechanical Keyboard', 25, 109.99, (SELECT id FROM t_category WHERE name = 'Keyboards')),
('Wireless Combo 1', 'Wireless Keyboard', 18, 69.99, (SELECT id FROM t_category WHERE name = 'Keyboards')),
('4K Monitor 1', '27-inch IPS Monitor', 30, 399.99, (SELECT id FROM t_category WHERE name = 'Monitors')),
('Office Monitor 1', '24-inch LED Monitor', 22, 179.99, (SELECT id FROM t_category WHERE name = 'Monitors'));

快传项目 2018-3月

# drawerlayout使用

    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, null,
          R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawerLayout.setDrawerListener(drawerToggle);
    drawerToggle.syncState();
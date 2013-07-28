#!/usr/bin/env python
# Programmer: Chris Bunch (shatterednirvana@gmail.com)


# General-purpose Python library imports
import os
import sys
import unittest


# Third party libraries
from flexmock import flexmock


# Import for the library that we're testing here
kmeans_dir = os.path.dirname(__file__) + os.sep + ".." + os.sep + ".." + \
  os.sep + "lib" + os.sep + "kmeans"
sys.path.append(kmeans_dir)
from cluster import Cluster


class TestCluster(unittest.TestCase):


  def test_nothing_yet(self):
    pass

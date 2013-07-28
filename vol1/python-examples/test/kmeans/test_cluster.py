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

data_dir = os.path.dirname(__file__) + os.sep + ".." + os.sep + ".." + \
  os.sep + "lib" + os.sep + "general" + os.sep + "data"
sys.path.append(data_dir)
from basic_data import BasicData


class TestCluster(unittest.TestCase):


  def test_dimensions(self):
    cluster = Cluster(3)
    self.assertTrue(len(cluster) > 0)
    self.assertEqual(3, cluster.get_dimensions())


  def test_center(self):
    cluster = Cluster(3)
    ob1 = [2.0, 10.0, 100.0]
    ob2 = [4.0, 20.0, 200.0]
    ob3 = [6.0, 30.0, 300.0]

    cluster.get_observations().add(BasicData(ob1))
    cluster.get_observations().add(BasicData(ob2))
    cluster.get_observations().add(BasicData(ob3))

    self.assertEqual(3, cluster.get_observations().size())

    cluster.calculate_center()

    self.assertAlmostEqual(4.0, cluster.get_center()[0], delta=0.00001)
    self.assertAlmostEqual(20.0, cluster.get_center()[1], delta=0.00001)
    self.assertAlmostEqual(200.0, cluster.get_center()[2], delta=0.00001)

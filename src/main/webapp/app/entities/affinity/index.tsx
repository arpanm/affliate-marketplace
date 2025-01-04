import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Affinity from './affinity';
import AffinityDetail from './affinity-detail';
import AffinityUpdate from './affinity-update';
import AffinityDeleteDialog from './affinity-delete-dialog';

const AffinityRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Affinity />} />
    <Route path="new" element={<AffinityUpdate />} />
    <Route path=":id">
      <Route index element={<AffinityDetail />} />
      <Route path="edit" element={<AffinityUpdate />} />
      <Route path="delete" element={<AffinityDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AffinityRoutes;
